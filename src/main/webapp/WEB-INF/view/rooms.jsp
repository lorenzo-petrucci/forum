<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
</head>
<body>
    <p>${roomList}</p>
    <c:forEach var="room" items="${roomList}">
        <p>${room.title()}</p>
    </c:forEach>
</body>
</html>