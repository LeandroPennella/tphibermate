<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- http://stackoverflow.com/questions/5313601/jquery-ui-draggable-droppable-breaks-after-ajax-post-to-controller-action-mvc -->
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="../ui/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="../ui/js/jquery-ui-1.11.4.js"></script>
<script type="text/javascript">
$(function () {
    $('.draggable').draggable({ revert: true });
    $('.droppable').droppable({
        drop: function (event, ui) {
            /*$.ajax({
                type: "POST",
                url: '/SiteSetup/GetMarkup/' + $(ui.draggable).attr("id"),
                success: function (data) {
                    $('.draggable').draggable('destroy');
                    $('#droppable').replaceWith(data);
                    $('.draggable').draggable({ revert: true });

                }
            });*/
            $( this )
            .addClass( "ui-state-highlight" )
            .find( "#droppable" )
              .html( "Dropped!" );
        }
    });
});
</script>
<style type="text/css">
.draggable{height:20px; width: 50px;background-color: grey;}
#droppable{height:30px; width: 100px; background-color: blue;}
</style>
</head>
<body>
<div id="drop1" class="droppable">
</div>
<div class="draggable">sarasa</div>
</body>
</html>