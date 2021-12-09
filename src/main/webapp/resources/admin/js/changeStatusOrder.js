
	
	$(document).on("click",".state_confirm", function () {
	
		let id = $(this).data("id");
			debugger
			
			
		if(confirm ("Are you sure you want to confirm this order?"))
		{
			$.ajax({
			url: "admin/order/changeOrderStatus",
			type: "POST",
			data: { id: id, state:2 },
			success: function(response) {
				
					alert("Confirm Success")
				window.location.reload();
				
				
			},
			error: function(e) {
				alert("Confirm Fail")
			}
			
		})
		}
		else
		{
			return
		}
	})
	$(document).on("click",".state_cancel", function () {
	
		console.log(this)
		let id = $(this).data("id");
		
		if(confirm ("Are you sure you want to cancel this order?"))
		{
			$.ajax({
			url: "admin/order/changeOrderStatus",
			type: "POST",
			data: { id: id,state:5 },
			success: function(response) {
				
                alert("Success")
				window.location.reload();
			},
			error: function(e) {
				alert("Fail")
			}
		})
		}
		else{
			return 
		}

	})
    $(document).on("click",".state_deliver", function () {
	
		console.log(this)
		let id = $(this).data("id");
		
		if(confirm ("Are you sure you want to confirm delivering?"))
		{
			$.ajax({
			url: "admin/order/changeOrderStatus",
			type: "POST",
			data: { id: id,state:3 },
			success: function(response) {
				
                alert("Success")
				window.location.reload();
			},
			error: function(e) {
				alert("Fail")
			}
		})
		}
		else{
			return 
		}
        
	})
    $(document).on("click",".state_delivered", function () {
	
		console.log(this)
		let id = $(this).data("id");
		
		if(confirm ("Are you sure you want to confirm delivered?"))
		{
			$.ajax({
			url: "admin/order/changeOrderStatus",
			type: "POST",
			data: { id: id,state:4 },
			success: function(response) {
				
                alert("Success")
				window.location.reload();
			},
			error: function(e) {
				alert("Fail")
			}
		})
		}
		else{
			return 
		}
        
	})

