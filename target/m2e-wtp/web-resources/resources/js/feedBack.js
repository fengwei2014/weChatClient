(function(){
	$saveBtn = $("#saveBtn");
	$clearBtn = $("#clearBtn");
	$feedBackContent = $("#feedBackContent");

	$clearBtn.on('click', function(event) {
		$feedBackContent.val("");
	});

	$saveBtn.on('click', function(event) {
		var contentVal = $feedBackContent.val();
		if (contentVal) {
			var postdata = JSON.stringify({feedback: contentVal});
			$.ajax({
				url: 'web/feedback',
				type: 'POST',
				dataType: 'json',
				contentType: 'application/json',
				data: postdata,
			})
			.done(function(data) {
				console.log("success");
				$feedBackContent.val("");
				alert(data.msg);
			})
			.fail(function() {
				console.log("error");
			})
			.always(function() {
				console.log("complete");
			});
		} else{
			alert("请填写反馈意见!")
		};
		
		
	});
})();