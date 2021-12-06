
	
	$(document).on("click",".state_block", function () {
	
		let idUser = $(this).data("id");
			
			console.log(this)
			
		if(confirm ("Are you sure you want to block this user?"))
		{
			$.ajax({
			url: "admin/user/changeStatus",
			type: "POST",
			data: { idUser: idUser, state:true },
			success: function(response) {
				
					console.log(response.state)
					$('#' + idUser).replaceWith("<i id=" +idUser+" class='fa fa-check' style=color:green aria-hidden=true ></i>");

			
				$('#status' + idUser).removeAttr('style').attr('style', 'color:red');
				$('#status' + idUser).empty().append('Block');
				$('#state_block'+idUser).removeAttr('class').attr('class', 'state_active')
				$('#state_block'+idUser).removeAttr('id').attr('id', 'state_active'+idUser)
				
				
				
			},
			error: function(e) {
				console.log("ERROR: ", e);
			}
			
		})
		}
		else
		{
			return
		}
	})
	$(document).on("click",".state_active", function () {
	
		console.log(this)
		let idUser =$(this).data("id");
		
		if(confirm ("Are you sure you want to open this user?"))
		{
			$.ajax({
			url: "admin/user/changeStatus",
			type: "POST",
			data: { idUser: idUser,state:false },
			success: function(response) {
				console.log(response.state)
				$('#' + idUser).replaceWith("<i id=" +idUser+" class='fa fa-ban' style=color:red aria-hidden=true ></i>");
				$('#status' + idUser).removeAttr('style').attr('style', 'color:green');
				$('#status' + idUser).empty().append('Active');
				$('#state_active'+idUser).removeAttr('class').attr('class', 'state_block')
				$('#state_active'+idUser).removeAttr('id').attr('id', 'state_block'+idUser)
			
				
			},
			error: function(e) {
				console.log("ERROR: ", e);
			}
		})
		}
		else{
			return 
		}
	})
