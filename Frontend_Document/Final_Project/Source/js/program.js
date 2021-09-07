// Trong bài tập này sẽ xử dụng Jquery và JavaScript, JavaScript để thực hiện chức năng quản lý nhân viên, tương ứng với bảng Account trong DB TestingSystem, Các chức năng: Thêm, Sửa, Xóa. Dữ liệu này sẽ tương tác với server Backend API.
// Khai báo 1 array để lưu thông tin tất cả các Account trên hệ thống.
var listAccount = [];
var listDepartment = [];
var listPosition = [];
// Hàm thực thi khi load đầy đủ các thành phần html
$(function () {
  // Gọi hàm load dữ liệu cho bảng Account từ Server API khi load trang
  getListEmployees();
  // Gọi hàm load dữ liệu cho bảng Department từ Server API khi load trang, sẽ đổ dữ liệu vào thẻ Select id="Department_ID" để chọn phòng ban
  getListDepartment();
  // Gọi hàm load dữ liệu cho bảng Possition từ Server API khi load trang, sẽ đổ dữ liệu vào thẻ  <select class="form-control" id="Position_ID"> để chọn Possition cho Account
  getListPosition();

  // Disable các trường CreateDate khi nhấn vào nút Save do trường này không cần nhập vào, sẽ lấy là Now Date khi Insert DB
  $("#Cretate_Date_ID").attr("disabled", "disabled");
  $("#ID_ID").attr("disabled", "disabled");

  // Xử lý sự kiện cho nút reset, Sử dụng JQuery để lấy các giá trị các trường theo ID, sau đó Set về rỗng
  $("#reset_btn").click(function () {
    $("#ID_ID").val("");
    $("#Email_ID").val("");
    $("#Username_ID").val("");
    $("#Fullname_ID").val("");
    $("#Department_ID").val("");
    $("#Position_ID").val("");
    $("#Cretate_Date_ID").val("");
  });

  // Xử lý sự kiện khi nhấn nút Save
  // Xử lý sự kiện khi nhấn nút Submit của Form(Save), ở đây phải sử dụng Return False ở cuối sự kiện này để không redirect sang trang mới.

  $("#Main_Form_ID").submit(function () {
    // Lấy các giá trị người dùng nhập vào
    var v_ID_ID = $("#ID_ID").val();
    var v_Email_ID = $("#Email_ID").val();
    var v_Username_ID = $("#Username_ID").val();
    var v_Fullname_ID = $("#Fullname_ID").val();
    var v_Department_ID = $("#Department_ID").val();
    var v_Position_ID = $("#Position_ID").val();
    // Lấy ra ID của Department khi người dùng lựa chọn phòng ban
    for (let index = 0; index < listDepartment.length; index++) {
      if (listDepartment[index].name == v_Department_ID) {
        var depID = listDepartment[index].id;
      }
    }
    // Lấy ra ID của Possition khi người dùng lựa chọn Possition
    for (let index = 0; index < listPosition.length; index++) {
      if (listPosition[index].name == v_Position_ID) {
        var posID = listPosition[index].id;
      }
    }
    // Tạo 1 đối tượng account để lưu giữ thông tin nhận được, ở đây khi tạo đối tượng không cần lấy ID do khi tạo dữ liệu trên server ID sẽ được tự động Gen
    var account = {
      email: v_Email_ID,
      username: v_Username_ID,
      fullname: v_Fullname_ID,
      departmentId: depID,
      positionId: posID,
    };

    // Add account tới MOCK API
    $.ajax({
      url: "http://localhost:8080/api/v1/accounts",
      type: "POST",
      data: JSON.stringify(account), // body
      contentType: "application/json", // type of body (json, xml, text)
      // dataType: 'json', // datatype return
      success: function (data, textStatus, xhr) {
        console.log(data);
        // success
        alert("Create Successful");
        getListEmployees();
      },
      error(jqXHR, textStatus, errorThrown) {
        alert("Error when loading data");
        console.log(jqXHR);
        console.log(textStatus);
        console.log(errorThrown);
      },
    });
    return false;
  });
});
// Đoạn bên trên khi load hoàn chỉnh trang web sẽ kết thúc ở đây, đoạn dưới sẽ viết các hàm bổ trợ khác

function getListDepartment() {
  // call API from server
  $.get("http://localhost:8080/api/v1/departments/", function (data, status) {
    // reset list employees
    listDepartment = [];
    // error
    if (status == "error") {
      // TODO
      alert("Error when loading data");
      return;
    }
    // success thì sẽ đổ dữ liệu vào ListDepartment và lấy dữ liệu trong List này fill vào phần thẻ Selecr để chọn Department, chú ý phần này phải để trong nội dung xử lý của Ajax
    data.forEach(function (item) {
      var department = {
        id: item.id,
        name: item.name,
      };
      listDepartment.push(department);
    });
    for (let index = 0; index < listDepartment.length; index++) {
      $("#Department_ID").append(`
      <option>${listDepartment[index].name}</option>
        `);
    }
  });
}

function getListPosition() {
  // call API from server
  $.get("http://localhost:8080/api/v1/positions/", function (data, status) {
    // reset list employees
    listPosition = [];
    // error
    if (status == "error") {
      // TODO
      alert("Error when loading data");
      return;
    }
    // success thì sẽ đổ dữ liệu vào listPossition và lấy dữ liệu trong List này fill vào phần thẻ Selecr để chọn Possition, chú ý phần này phải để trong nội dung xử lý của Ajax
    data.forEach(function (item) {
      var position = {
        id: item.id,
        name: item.name,
      };
      listPosition.push(position);
    });
    for (let index = 0; index < listPosition.length; index++) {
      $("#Position_ID").append(`
      <option>${listPosition[index].name}</option>
        `);
    }
  });
}

// Viết hàm showAccount()
function showAccount() {
  $("#Email_ID").removeAttr("disabled");
  $("#Username_ID").removeAttr("disabled");
  // Xóa hết kết quả đang hiển thị ở bảng kết quả
  $("#Result_TB").empty();
  // Lặp trong listAccount để in thông tin từng phần tử
  // Hiển thị thêm 2 nút để sửa và xóa các Account
  for (var index = 0; index < listAccount.length; index++) {
    $("#Result_TB").append(`
                <tr>
                <th>${listAccount[index].AccountID}</th>
                <th>${listAccount[index].Email}</th>
                <th>${listAccount[index].Username}</th>
                <th>${listAccount[index].FullName}</th>
                <th>${listAccount[index].Department}</th>
                <th>${listAccount[index].Position}</th>   
                <th>${listAccount[index].CreateDate}</th>
                <th><button class="btn btn-warning" onclick="editAccount(${index})">Edit</button></th>
                <th><button class="btn btn-warning" onclick="deleteAccount(${index})">Delete</button></th>
                </tr>
                `);
  }
}

// Viết hàm xóa account
function deleteAccount(Index) {
  var v_del_ID = listAccount[Index].AccountID;
  // Hiển thị 1 Confim Popup, chọn Có = True
  var confirm_del = confirm("Bạn có chắc chắn muốn xóa Account này không");
  if (confirm_del) {
    $.ajax({
      url: "http://localhost:8080/api/v1/accounts/" + v_del_ID,
      type: "DELETE",
      success: function (result) {
        // error
        if (result == undefined || result == null) {
          alert("Error when loading data");
          return;
        }
         // success
        getListEmployees();
      },
    });
  } else {
    return;
  }
}
// Viết hàm để Edit các account
function editAccount(Index) {
  // Lấy giá trị các trường ID, Fullname, Department, Possition Fill vào Form
  $("#ID_ID").val(listAccount[Index].AccountID);
  $("#Fullname_ID").val(listAccount[Index].FullName);
  $("#Department_ID").val(listAccount[Index].Department);
  $("#Position_ID").val(listAccount[Index].Position);
  // Disable các trường Email, Username, CreateDate khi nhấn vào nút Edit do không cho cập nhật các trường này
  $("#Email_ID").attr("disabled", "disabled");
  $("#Username_ID").attr("disabled", "disabled");
  $("#Cretate_Date_ID").attr("disabled", "disabled");
  //  Xử lý sự kiện khi click vào nút Update
  $("#update_btn").click(function () {
    var v_ID_ID = $("#ID_ID").val();
    var v_Fullname_ID = $("#Fullname_ID").val();
    var v_Fullname_ID = $("#Fullname_ID").val();
    var v_Department_ID = $("#Department_ID").val();
    var v_Position_ID = $("#Position_ID").val();
    // Lấy ra ID của Department khi người dùng lựa chọn phòng ban
    for (let index = 0; index < listDepartment.length; index++) {
      if (listDepartment[index].name == v_Department_ID) {
        var depID = listDepartment[index].id;
      }
    }
    // Lấy ra ID của Possition khi người dùng lựa chọn Possition
    for (let index = 0; index < listPosition.length; index++) {
      if (listPosition[index].name == v_Position_ID) {
        var posID = listPosition[index].id;
      }
    }
    // Ở đây chỉ cho người dùng update các trường, fullname, Department, Possition, các trường Username, Email trên thực tế sẽ để cố định.
    var account = {
      fullname: v_Fullname_ID,
      departmentId: depID,
      positionId: posID,
    };
     $.ajax({
      url: "http://localhost:8080/api/v1/accounts/" + v_ID_ID,
      type: "PUT",
      data: JSON.stringify(account), // body
      contentType: "application/json", // type of body (json, xml, text)
      // dataType: 'json', // datatype return
      success: function (data, textStatus, xhr) {
        console.log(data);
        // success
        alert("Update Successful");
        getListEmployees();
      },
      error(jqXHR, textStatus, errorThrown) {
        alert("Error when loading data");
        console.log(jqXHR);
        console.log(textStatus);
        console.log(errorThrown);
      },
    });
  });
}

/// Viết hàm gọi dữ liệu server API bằng Ajax khi load trang Web
function getListEmployees() {
  // call API from server
  $.get("http://localhost:8080/api/v1/accounts/", function (data, status) {
    // reset list employees
    listAccount = [];
    // error
    if (status == "error") {
      // TODO
      alert("Error when loading data");
      return;
    }
    // success
    parseData(data);
    showAccount();
  });
}

// hàm chuyển dữ liệu API nhận được bằng Ajax chuyển về dạng List để sử dụng trong chương trình
function parseData(data) {
  // employees = data;
  data.forEach(function (item) {
    var account = {
      AccountID: item.id,
      Email: item.email,
      Username: item.username,
      FullName: item.fullname,
      Department: item.department,
      Position: item.position,
      CreateDate: item.createDate,
    };
    listAccount.push(account);
  });
}