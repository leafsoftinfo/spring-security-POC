//alias the remote validator method and add the custom message.
$.validator.addMethod("checkUserName", $.validator.methods.remote,
"Username already exists!");

$.validator.addClassRules({
	
    usernameField: {
	 minlength: 4,
	 required: true,
	 maxlength: 20
    },
	nameField: {
		required: true,
		minlength: 2,
		maxlength: 50
    },
    passwordField: {
        required: true,
        minlength: 4,
        maxlength: 20
    },
    emailField: {
    	 email: true,
         maxlength: 50,
         required: true
    },
    //customer method which Checks remotely if the username already exists.
    usernameCheckRemotely: {
	
	 minlength: 4,
	 required: true,
	 maxlength: 20,
	 //Do a remote checking if username is already taken.
	 checkUserName:{
		 url: "../remote/checkUserName", //make sure to return true or false with a 200 status code
		 type: "GET"
	 }
     
    }
    
});


//defined a customized function to avoid duplication.
function validateForm(arg){
	_form = arg;
	$(_form).validate({
        highlight: function (element) {
            $(element).closest('.control-group').removeClass('success').addClass('error');
        },
        success: function (element) {
            element.text('OK!').addClass('valid')
                .closest('.control-group').removeClass('error').addClass('success');
        }
    });
	
}