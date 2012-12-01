
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
<script type="text/javascript">

    $("form .send").live("click", function(){
    
       	var response_area = $(this).siblings("textarea.result");
    
    
    	var url = $(this).parent().attr('action');
    	var id = $(this).siblings("input.id").val();
    	if ( typeof(id) !== 'undefined' ) {
    		url += '/'+id;
    	}
    	
    	
    	var data = '';
    	$.each($(this).siblings("input"),function(i,field){
    		if ( field.className != 'id' && field.value != '' ) {
    			data += '&'+field.className+'='+field.value;
    		}
    	})    	
    	
		$.ajax({
		    url: url,
		    type: $(this).parent().attr('method'),
		    data: data,
		    dataType: 'text',
		    success: function(result) {
       			response_area.val('URL : '+url+' | '+data+'\n\n'+result);
    		},
    		error: function(response) {
    			response_area.val('URL : '+url+' | '+data+'Bad response status  : ' + response.status);
    		}

		})

    	return false;
    });
</script>

