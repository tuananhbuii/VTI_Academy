package com.vti.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.vti.entity.Account;
import com.vti.entity.AccountStatus;
import com.vti.entity.Department;
import com.vti.entity.Position;
import com.vti.entity.RegistrationUserToken;
import com.vti.entity.ResetPasswordToken;
import com.vti.event.OnResetPasswordViaEmailEvent;
import com.vti.event.OnSendRegistrationUserConfirmViaEmailEvent;
import com.vti.form.AccountFilter;
import com.vti.form.AccountFormForCreating;
import com.vti.form.AccountFormForCreatingRegister;
import com.vti.form.AccountFormForUpdating;
import com.vti.repository.IAccountRepository;
import com.vti.repository.IDepartmentRepository;
import com.vti.repository.IPositionRepository;
import com.vti.repository.RegistrationUserTokenRepository;
import com.vti.repository.ResetPasswordTokenRepository;
import com.vti.specification.AccountSpecification;

@Service
@Component
@Transactional
public class AccountService implements IAccountService {

	@Autowired
	private IAccountRepository accountRepository;

	@Autowired
	private IDepartmentRepository departmentRepository;

	@Autowired
	private IPositionRepository positionRepository;

	@Autowired
	private RegistrationUserTokenRepository registrationUserTokenRepository;

	@Autowired
	private ResetPasswordTokenRepository resetPasswordTokenRepository;
	@Autowired
	private ApplicationEventPublisher eventPublisher;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public UserDetails loadUserByUsername(String username) {
		Account account = accountRepository.findByUsername(username);
		if (account == null) {
			throw new UsernameNotFoundException(username);
		}
		return new User(account.getUsername(), account.getPassword(),
				AuthorityUtils.createAuthorityList(account.getRole().toString()));
	}

	@Override
	@SuppressWarnings("deprecation")

	public Page<Account> getAllAccounts(Pageable pageable, String search, AccountFilter filter) {
		Specification<Account> where = null;
		if (!StringUtils.isEmpty(search)) {
			AccountSpecification usernameSpecification = new AccountSpecification("username", "LIKE", search);
			AccountSpecification departmentSpecification = new AccountSpecification("department", "LIKE", search);
			where = Specification.where(usernameSpecification).or(departmentSpecification);
		}
		if (filter != null && filter.getMinDate() != null) {
			AccountSpecification minDateSpecification = new AccountSpecification("createDate", ">=",
					filter.getMinDate());
			if (where == null) {
				where = Specification.where(minDateSpecification);
			} else {
				where = where.and(minDateSpecification);
			}
		}
		if (filter != null && filter.getMaxDate() != null) {
			AccountSpecification maxDateSpecification = new AccountSpecification("createDate", "<=",
					filter.getMaxDate());
			if (where == null) {
				where = Specification.where(maxDateSpecification);
			} else {
				where = where.and(maxDateSpecification);
			}
		}
		return accountRepository.findAll(where, pageable);
	}

	@Override
	public Account getAccountByID(short id) {
		// TODO Auto-generated method stub
		return accountRepository.getById(id);
	}

	@Override
	public void createAccount(AccountFormForCreating form) {
		Account account = new Account();
		Department department = departmentRepository.getById(form.getDepartmentId());
		Position position = positionRepository.getById(form.getPositionId());
		account.setEmail(form.getEmail());
		account.setUsername(form.getUsername());
		account.setFullname(form.getFullname());
		account.setDepartment(department);
		account.setPosition(position);
		accountRepository.save(account);

	}

	@Override
	public void updateAccount(short id, AccountFormForUpdating form) {
		Account account = accountRepository.getById(id);
		Department department = departmentRepository.getById(form.getDepartmentId());
		Position position = positionRepository.getById(form.getPositionId());
		account.setFullname(form.getFullname());
		account.setDepartment(department);
		account.setPosition(position);
		accountRepository.save(account);

	}

	@Override
	public void deleteAccount(short id) {
		accountRepository.deleteById(id);
	}

	@Override
	public void deleteAccounts(List<Short> ids) {
		accountRepository.deleteByIds(ids);
	}

	@Override
	public Account getAccountByUsername(String username) {
		// TODO Auto-generated method stub
		return accountRepository.findByUsername(username);
	}

	@Override
	public void createAccountRegister(AccountFormForCreatingRegister form) {
		Account account = new Account();
		Department department = departmentRepository.getById(form.getDepartmentId());
		Position position = positionRepository.getById(form.getPositionId());
		account.setEmail(form.getEmail());
		account.setUsername(form.getUsername());
		account.setFullname(form.getFullname());
		account.setDepartment(department);
		account.setPosition(position);
		account.setPassword(passwordEncoder.encode(form.getPassword()));
		accountRepository.save(account);

		// create new user registration token
		createNewRegistrationUserToken(account);

		// send email to confirm
		sendConfirmUserRegistrationViaEmail(account.getEmail());

	}

	private void createNewRegistrationUserToken(Account account) {

		// create new token for confirm Registration
		final String newToken = UUID.randomUUID().toString();
		RegistrationUserToken token = new RegistrationUserToken(newToken, account);

		registrationUserTokenRepository.save(token);

	}

	@Override
	public Account getAccountByEmail(String email) {
		// TODO Auto-generated method stub
		return accountRepository.findByEmail(email);
	}

	@Override
	public void activeUser(String token) {
		RegistrationUserToken registrationUserToken = registrationUserTokenRepository.findByToken(token);

		Account account = registrationUserToken.getAccount();
		account.setStatus(AccountStatus.ACTIVE);

		accountRepository.save(account);

		// remove Registration User Token
		registrationUserTokenRepository.deleteById(registrationUserToken.getId());

	}

	@Override
	public void sendConfirmUserRegistrationViaEmail(String email) {
		eventPublisher.publishEvent(new OnSendRegistrationUserConfirmViaEmailEvent(email));

	}

	@Override
	public boolean existsUserByEmail(String email) {
		// TODO Auto-generated method stub
		return accountRepository.existsByEmail(email);
	}

	@Override
	public boolean existsUserByUsername(String username) {
		// TODO Auto-generated method stub
		return accountRepository.existsByUsername(username);
	}

	@Override
	public void resetPasswordViaEmail(String email) {
		// find account by email
		Account account = getAccountByEmail(email);
		// remove token token if exists
		registrationUserTokenRepository.deleteByUserId(account.getId());
		// create new token reset password
		createNewResetPasswordToken(account);
		// send email
		sendResetPasswordViaEmail(email);

	}

	private void createNewResetPasswordToken(Account account) {
		// create new token for Reseting password
		final String newToken = UUID.randomUUID().toString();
		ResetPasswordToken token = new ResetPasswordToken(newToken, account);
		resetPasswordTokenRepository.save(token);

	}

	@Override
	public void resetPassword(String token) {
		// get token
		ResetPasswordToken resetPasswordToken = resetPasswordTokenRepository.findByToken(token);
		// chage password
		Account account = resetPasswordToken.getAccount();
		account.setPassword(passwordEncoder.encode("111111"));
		accountRepository.save(account);
		// remove Reset Password
		resetPasswordTokenRepository.deleteById(resetPasswordToken.getId());
	}

	@Override
	public void sendResetPasswordViaEmail(String email) {
		eventPublisher.publishEvent(new OnResetPasswordViaEmailEvent(email));

	}

	@Override
	public void changePassword(String username, String newPassword) {
		Account account = accountRepository.findByUsername(username);
		account.setPassword(passwordEncoder.encode(newPassword));
		accountRepository.save(account);
	}

}
