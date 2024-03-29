$(function () {
  hideMessageErrorValidate();
  document.getElementById("check").unchecked = storage.isLocal();
});
$("#password").on("keypress", function (e) {
  if (e.which == 13) {
    login();
  }
});

$("#signinForm").submit(function () {
  // get username & password
  var username = document.getElementById("username").value;
  var password = document.getElementById("password").value;

  // call api
  $.ajax({
    url: "http://localhost:8080/api/v1/login",
    type: "GET",
    contentType: "application/json",
    dataType: "json",
    beforeSend: function (xhr) {
      xhr.setRequestHeader(
        "Authorization",
        "Basic " + btoa(username + ":" + password)
      );
    },
    success: function (data, textStatus, xhr) {
      console.log(data);
      storage.setLocal(document.getElementById("check").checked);
      if (data.status == "NOT_ACTIVE") {
        swal ( "Info!", "Your account has not been activated " , "info" );
        return false;
      }
      // save data to cookie
      storage.setItem("ID", data.id);
      storage.setItem("EMAIL", data.email);
      storage.setItem("FULLNAME", data.fullname);
      storage.setItem("ROLE", data.role);
      storage.setItem("USERNAME", username);
      storage.setItem("PASSWORD", password);
      hideMessageErrorValidate();
      // redirect to Home
      window.location.replace("index.html");
    },
    error(jqXHR, textStatus, errorThrown) {
      if (jqXHR.status == 401) {
        showMessageErrorValidate("Wrong Account or Password!");
      }
      resetForm();
      console.log(jqXHR);
      console.log(textStatus);
      console.log(errorThrown);
    },
  });
  return false;
});

function showMessageErrorValidate(message) {
  document.getElementById("error-message").style.display = "block";
  document.getElementById("error-message").innerHTML = message;
}

function hideMessageErrorValidate() {
  document.getElementById("error-message").style.display = "none";
}
function HideModalReset() {
  $("#reset_password_form").modal("hide");
}
function forgotPassword() {
  $("#email_password").val("");
  $("#reset_password_form").modal("show");
}
function Send_Password() {
  // get data
  var v_Email_ID = $("#email_password").val();
  // validate
  if (!v_Email_ID || v_Email_ID.length < 6 || v_Email_ID.length > 50) {
    // show error message
    document.getElementById("error-email-password").innerHTML =
      "Email không đúng định dạng!";
    return false;
  }
  $.ajax({
    url: "http://localhost:8080/api/v1/accounts/email/" + v_Email_ID,
    type: "GET",
    contentType: "application/json",
    dataType: "json", // datatype return
    success: function (data, textStatus, xhr) {
      if (!data) {
        document.getElementById("error-email-password").innerHTML =
          "Email không tồn tại !!";
        return false;
      } else {
        $.ajax({
          url:
            "http://localhost:8080/api/v1/newPassword/resetPasswordRequest?email=" +
            v_Email_ID,
          type: "GET",
          // data: JSON.stringify(account), // body
          // contentType: "application/json",
          // dataType: "json", // type of body (json, xml, text)
          // datatype return
          success: function (data, textStatus, xhr) {
            console.log(data);
            HideModalReset();
            swal ( "Success", "We have sent an email. Please check email to reset password!" , "success" );
          },
          error(jqXHR, textStatus, errorThrown) {
            swal ( "Error!", "Error when loading data" , "error" );
            console.log(jqXHR);
            console.log(textStatus);
            console.log(errorThrown);
          },
        });
      }
      document.getElementById("error-email-password").style.display = "none";
    },
    error(jqXHR, textStatus, errorThrown) {
      swal ( "Error!", "Error when loading data" , "error" );
      console.log(jqXHR);
      console.log(textStatus);
      console.log(errorThrown);
    },
  });
  return false;
}
