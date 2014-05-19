<link href="/resources/css/login.css" rel="stylesheet">
<form class="form-signin" role="form" action="/register" method="post">
    <h2 class="form-signin-heading">New User</h2>
    <span class="error"></span>
    <span class="message"></span>
    <input type="email" class="form-control" placeholder="Email address" required autofocus name="email">
    <input type="password" class="form-control" placeholder="Password" required name="password">
    <button class="btn btn-lg btn-primary btn-block" id="submitButton" type="submit">Register</button>
</form>
<script type="text/javascript">
    $(function(){
        function createUser(){
            $.ajax({
                url:'http://localhost:8500/user',
                type:'POST',
                contentType: 'application/json',
                data:JSON.stringify({
                    email: $('[name="email"]').val(),
                    password: $('[name="password"]').val()
                }),
                complete:function(r){
                    if(r.status == 201){
                        $('.error').html('').hide('fast');
                        $('.message').html('User created successfully').show('fast');
                        setTimeout(function(){
                            window.location.href='/index.html';
                        },1000);
                    }else{
                        $('.message').html('').hide('fast');
                        $('.error').html('Error: '+r.statusText).show('fast');
                    }
                }
            });
        }
        $('body').on('keyup keypress',function(e){
           if(e.which == 13){
               e.preventDefault();
               createUser();
           }
        });
        $('#submitButton').on('click',function(e){
            e.preventDefault();
            createUser();
        });
    });
</script>