//var form = document.getElementById("webform-submission-double-happy-form");
//form.onsubmit = function (e) {
//  // stop the regular form submission
//  e.preventDefault();
//
//  // collect the form data while iterating over the inputs
//  var data = {};
//  for (var i = 0, ii = form.length; i < ii; ++i) {
//    var input = form[i];
//    if (input.name) {
//      data[input.name] = input.value;
//    }
//  }
//
//function addData(){
// $.ajax({
//         type: "POST",
//         url: "http://example.com",
//         data: JSON.stringify(data),
//         contentType: "application/json; charset=utf-8",
//         crossDomain: true,
//         dataType: "json",
//         success: function (data, status, jqXHR) {
//
//             alert(success);
//         },
//
//         error: function (jqXHR, status) {
//             // error handler
//             console.log(jqXHR);
//             alert('fail' + status.code);
//         }
//      });
//}