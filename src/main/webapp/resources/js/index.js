$('.login-page .login-form .browse').click(function(){
   $(document.forms[0]).animate({height: "toggle", opacity: "toggle"}, "slow");
   $(document.forms[2]).animate({height: "toggle", opacity: "toggle"}, "slow");
});

$('.login-page .login-form .search').click(function(){
   $(document.forms[1]).animate({height: "toggle", opacity: "toggle"}, "slow");
   $(document.forms[2]).animate({height: "toggle", opacity: "toggle"}, "slow");
});

$('.back1').click(function(){
  $(document.forms[0]).animate({height: "toggle", opacity: "toggle"}, "slow");
  $(document.forms[2]).animate({height: "toggle", opacity: "toggle"}, "slow");
});

$('.back2').click(function(){
  $(document.forms[1]).animate({height: "toggle", opacity: "toggle"}, "slow");
  $(document.forms[2]).animate({height: "toggle", opacity: "toggle"}, "slow");
});
