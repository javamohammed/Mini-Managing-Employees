<%@ include file="../layouts/header.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<meta name="theme-color" content="#563d7c">
<%@ include file="../assets/css/dashboard.css" %>
<title>Dashboard</title>
  </head>
  <body>
    <%@ include file="../layouts/menubar.jsp" %>

<div class="container-fluid">
  <div class="row">
    <%@ include file="../layouts/sidebar.jsp" %>

    <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
      <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
      </div>

      <h2>Dashboard</h2>
       <c:if test="${ !empty added }">
		      	<div class="alert alert-success" role="alert">
				  	<c:out value="${added }"/>
				</div>
		      </c:if>
      <div class="table-responsive">
        <table class="table table-striped table-sm">
          <thead>
            <tr>
              <th>#</th>
              <th>Name</th>
              <th>Email</th>
              <th>Phone</th>
              <th>HireDate</th>
              <th>Salary</th>
              <th></th>
              <th></th>
            </tr>
          </thead>
          <tbody>
          <c:forEach items="${employees}" var="employee" >
          <c:set var = "id" value = "${ employee.id }" />
          	<tr>
          	  <td><c:out value="${employee.id}"/></td>
               <td><c:out value="${employee.name }"/></td>
               <td><c:out value="${employee.email }"/></td>
               <td><c:out value="${employee.tel }"/></td>
               <td><c:out value="${employee.dateToString}"/></td>
               <td><c:out value="${employee.salary }"/></td>
              <td><a href="/Mini-Managing-Employees/edit?id=${id}" class="btn btn-outline-success btn-sm" >Edit</a></td>
              <td><a href="/Mini-Managing-Employees/delete?id=${id}" class="btn btn-outline-danger btn-sm" >Delete</a></td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
      </div>
    </main>
  </div>
</div>

<%@ include file="../layouts/footer.jsp" %>