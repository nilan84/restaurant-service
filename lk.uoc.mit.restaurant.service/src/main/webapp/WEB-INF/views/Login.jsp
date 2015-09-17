<script>
$(document).ready(function() {
	$('#sampleForm').submit(
		function(event) {
		       var message;
               message = document.getElementById("message");
               message.innerHTML = "";
               document.getElementById("message").style.visibility = 'hidden';
			var username = $('#username').val();
			var passward = $('#passward').val();
			var data = 'username='
					+ encodeURIComponent(username)
					+ '&passward='
					+ encodeURIComponent(passward);
		    if(!username){
		       document.getElementById("message").style.visibility = 'visible';
               message.innerHTML = "Please enter username and password !" ;
		    }
		    else{
			$.ajax({
				url : $("#sampleForm").attr("action"),
				data : data,
				type : "GET",

				success : function(response) {

					$('#login').hide();
					location.reload();

				},
				error : function(xhr, status, error) {
				   document.getElementById("message").style.visibility = 'visible';
				    message.innerHTML = "Password or username incorrect!" ;

				}
			});
			}
			return false;
		});
	});

</script>

<div class="modal fade" id="login" role="dialog"  aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-body">
       <div class="panel panel-info">
          <div class="panel-heading">
            <h3 class="panel-title">Please Sign In
              <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button></h3>

          </div>
          <div class="panel-body">
        <div id="message" class="alert alert-danger"  style="visibility:hidden;" >
        </div>
          <div class="row" >

        <div class="col-md-5" >
        <a href="#"><img src="http://techulus.com/buttons/fb.png" /></a><br/>
        <a href="#"><img src="http://techulus.com/buttons/tw.png" /></a><br/>
        <a href="#"><img src="http://techulus.com/buttons/gplus.png" /></a>
        </div>

            <div class="col-md-7" style="border-left:1px solid #ccc;height:160px">
        <form class="form-horizontal" id="sampleForm"  action="profile" >
        <fieldset>

          <input id="username" name="textinput" type="text" placeholder="Enter User Name" class="form-control input-md">
          <div class="spacing"><input type="checkbox" name="checkboxes" id="checkboxes-0" value="1"><small> Remember me</small></div>
          <input id="passward" name="textinput" type="password" placeholder="Enter Password" class="form-control input-md">
          <div class="spacing"><a href="#"><small> Forgot Password?</small></a><br/></div>
          <button id="passward" name="singlebutton" class="btn btn-info btn-sm pull-right">Sign In</button>


        </fieldset>
        </form>
        </div>
              </div>
           </div>
        </div>
       </div>
    </div>
  </div>
</div>

