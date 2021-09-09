function login(){
  var username = document.getElementsByName("username").value;
  var password = document.getElementsByName("password").value;

  $.ajax({
    url: 'http://localhost:8080/api/v1/login',
    type: 'GET',
    contentType: "application/json",
    dataType: 'json',
    beforeSend: function (xhr) {
        xhr.setRequestHeader("Authorization", "Basic " + btoa(username + ":" + password));
    },
    success: function (data, textStatus, xhr) {
}