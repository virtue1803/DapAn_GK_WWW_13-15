<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Home Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>
    <div class="container-fluid text-center">
        <form class="container-fluid" action="${pageContext.request.contextPath}/controller" method="post">
            <label class="form-label">Type search:</label>
            <select name="type_search" >
                <option value="amount">Money</option>
                <option value="ownerName">Owner Name</option>
            </select>
            <input type="text" name="inputSearch"/>
            <button class="btn btn-primary" type="submit">Search</button>
        </form>
    </div>
    <div class="container-fluid text-center">
        <h1>List Accounts</h1>
        <table class="table table-hover">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Owner Name</th>
                    <td>Owner Address</td>
                    <td>Amount</td>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="account" items="${accounts}">
                    <tr>
                        <td>${account.accountNumber}</td>
                        <td>${account.ownerName}</td>
                        <td>${account.ownerAddress}</td>
                        <td>${account.accountNumber}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
