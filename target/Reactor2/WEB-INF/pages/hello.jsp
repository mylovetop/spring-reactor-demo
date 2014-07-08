<html>
<head>
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <script>
        $(document).ready(function(){
            $.get( "http://localhost:8080/promise" ).then(
                    function(data, c, b) {
                        alert( "$.get succeeded promise : " + JSON.stringify(data) );
                    }, function(data) {
                        alert( "$.get failed!" );
                    }
            );

//            $.get( "http://localhost:8080/promise1" ).then(
//                function(data, c, b) {
//                    alert( "$.get succeeded promise1 : " + JSON.stringify(data) );
//                }, function(data) {
//                    alert( "$.get failed!" );
//                }
//            );

//            $.when( $.ajax( "http://localhost:8080/promise" ) ).then(function( data, textStatus, jqXHR ) {
//                alert( JSON.stringify(data) ); // Alerts 200
//            });

//            $.get( "http://localhost:8080/test" ).then(
//                    function(data, c, b) {
//                        alert( "$.get succeeded : " + JSON.stringify(data) );
//                    }, function(data) {
//                        alert( "$.get failed!" );
//                    }
//            );



//            $.get( "http://localhost:8080/test1" ).then(
//                    function(data, c, b, e, f, g) {
//                        alert( "$.get succeeded : " + JSON.stringify(data) );
//                    }, function(data) {
//                        alert( "$.get failed!" );
//                    }
//            );
        });

    </script>
</head>
<body>

	<h1>${message}</h1>
</body>
</html>