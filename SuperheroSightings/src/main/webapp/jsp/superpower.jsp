<%-- 
    Document   : superpower
    Created on : Oct 19, 2018, 10:44:06 PM
    Author     : Ryan
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>H.E.R.O. Superpower</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">      
        <link href="${pageContext.request.contextPath}/css/superherosightings.css" rel="stylesheet">
    </head>
    <body>
        <div class="container-fluid">
            <h1>H.E.R.O. Sightings Database</h1>
            <hr/>
            <div class="navbar">
                <nav class="navbar navbar-expand-lg navbar-light bg-light container-fluid">                   
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                        <div class="navbar-nav">
                            <a class="nav-item nav-link" href="${pageContext.request.contextPath}/home">Home</a>
                            <a class="nav-item nav-link" href="${pageContext.request.contextPath}/displayArchetypes">Archetype</a>
                            <a class="nav-item nav-link" href="${pageContext.request.contextPath}/displayLocations">Location</a>
                            <a class="nav-item nav-link" href="${pageContext.request.contextPath}/displayOrganizations">Organization</a>
                            <a class="nav-item nav-link" href="${pageContext.request.contextPath}/displaySightings">Sighting</a>
                            <a class="nav-item nav-link active" href="${pageContext.request.contextPath}/displaySuperpowers">Superpower</a>
                        </div>
                    </div>
                </nav>

            </div>
            <div class="row">
                <div class="col-md-4">
                    <h2>Superpower</h2>
                    <form class="form" role="form" method="POST" action="addSuperpower">
                        <div class="form-group row ">
                            <div class="col-md-12">
                                <label for="superpowerId" class="col-form-label">Superpower Id:</label>
                                <input type="text" class="col-sm-9 form-control" name="superpowerId" id="superpowerId"   readonly/>
                            </div>
                            <div class="col-md-12">
                                <label for="superpowerType" class="col-form-label">Superpower Type:</label>
                                <input type="text" class="col-sm-9 form-control" name="superpowerType"  id="superpowerType" placeholder="Superpower Type"/>
                                <div>
                                    <button type="submit" value="Add/Update Superpower" class="btn btn-default form-group">Add/Update</button>
                                    <button type="reset" class="btn btn-default form-group">Reset</button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="col-md-8">
                    <h2>Superpowers Identified</h2>
                    <table id="superpowerTable" class="table table-striped">
                        <thead class="col-md-12">
                            <tr>
                                <th  scope="col">Superpower ID</th>
                                <th scope="col">Superpower Type</th>
                                <th></th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="currentSuperpower" items="${superpowerList}">
                                <tr>
                                    <th scope="row">
                                        <c:out value="${currentSuperpower.superpowerId}"/> 
                                    </th>
                                    <td>
                                        <c:out value="${currentSuperpower.superpowerType}"/>
                                    </td>
                                    <td>
                                        <a href="#" onclick="editSuperpower('${currentSuperpower.superpowerId}', '${currentSuperpower.superpowerType}')"> 
                                            Edit
                                        </a>
                                    </td>
                                    <td>
                                        <a href="deleteSuperpower?superpowerId=${currentSuperpower.superpowerId}">
                                            Delete 
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/superpower.js"></script>

    </body>
</html>
