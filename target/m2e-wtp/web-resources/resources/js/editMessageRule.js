(function(){
  $careateRuleBtn = $("#careateRuleBtn");
  
  /**
  *获取全部规则数据
  */
   var getAllRules = function(){
	   	$.ajax({
	   	  url: 'messageRule',
	   	  type: 'GET',
	   	  dataType: 'json',
	   	  data: {q: 'all'},
	   	})
	   	.done(function(data) {
	   	  $("#ruleTBody").html("");
	   	  showAllRules(data);
	   	})
	   	.fail(function() {
	   	  console.log("error");
	   	})
	   	.always(function() {
	   	  console.log("complete");
	   	});
   };

  /**
  *展示全部规则数据
  */
  var showAllRules = function(data){
    var resultHtml = "";
    for (var i = 0; i < data.length; i++) {
     
      resultHtml += "<tr><td>"+ data[i].id+
                      "</td><td>"+ data[i].code+
                      "</td><td>"+ data[i].reply+
                      "</td><td>"+ data[i].reply+
                    "</td></tr>";
    };
    
    $("#ruleTBody").html(resultHtml);
  };

  getAllRules();

  $careateRuleBtn.on('click', function(event) {
    $code = $("#code");
    $reply = $("#reply");

    $.ajax({
      url: 'messageRule',
      type: 'POST',
      dataType: 'json',
      data: {code: $code.val(),
             reply: $reply.val()},
    })
    .done(function(data) {
      console.log("success");
      if(data.success){
      	$("#createBox").modal('hide');
      	getAllRules();
      	$code.val("");
    	$reply.val("");
      }
    })
    .fail(function() {
      console.log("error");
    })
    .always(function() {
      console.log("complete");
    });
    
  });

})();


