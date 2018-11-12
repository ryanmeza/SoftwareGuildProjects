<%-- 
    Document   : organization
    Created on : Oct 19, 2018, 10:44:27 PM
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
        <title>H.E.R.O. Organization</title>
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
                            <a class="nav-item nav-link active" href="${pageContext.request.contextPath}/displayOrganizations">Organization</a>
                            <a class="nav-item nav-link" href="${pageContext.request.contextPath}/displaySightings">Sighting</a>
                            <a class="nav-item nav-link" href="${pageContext.request.contextPath}/displaySuperpowers">Superpower</a>

                        </div>
                    </div>
                </nav>

            </div>
            <div class="row">
                <div class="col-md-3">
                    <h2>Organization</h2>
                    <form class="form" role="form" method="POST" action="addOrganization">
                        <div class="form-group row ">
                            <div class="col-md-12">
                                <label for="organizationId" class="col-form-label">Organization Id:</label>
                                <input type="text" class="col-sm-9 form-control" name="organizationId" id="organizationId" readonly/>
                            </div>
                            <div class="col-md-12">
                                <label for="organizationName" class="col-form-label">Organization Name:</label>
                                <input type="text" class="col-sm-9 form-control" name="organizationName"  id="organizationName" placeholder="Name"/>
                                <label for="organizationDescription" class="col-form-label">Organization Description:</label>
                                <input type="text" class="col-sm-9 form-control" name="organizationDescription"  id="organizationDescription" placeholder="Description"/>
                                <label for="organizationPhone" class="col-form-label">Organization Phone:</label>
                                <input type="text" class="col-sm-9 form-control" name="organizationPhone"  id="organizationPhone" placeholder="Phone"/>
                                <label for="organizationEmail" class="col-form-label">Organization Email:</label>
                                <input type="text" class="col-sm-9 form-control" name="organizationEmail"  id="organizationEmail" placeholder="Email"/>
                                <label for="locationId" class="col-form-label">Location ID:</label>
                                <select class="col-sm-9 form-control" name="locationId"  id="locationId">
                                    <c:forEach var="currentLocation" items="${locationList}">
                                        <option>
                                            <c:out value="${currentLocation.locationId}"/>
                                        </option>
                                    </c:forEach>
                                </select>
                                <div>
                                    <button type="submit" value="Add/Update Organization" class="btn btn-default form-group">Add/Update</button>
                                    <button type="reset" class="btn btn-default form-group">Reset</button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="col-md-9">
                    <h2>Organizations Identified</h2>
                    <table id="organizationTable" class="table table-striped">
                        <thead class="col-md-12">
                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">Name</th>
                                <th scope="col">Description</th>
                                <th scope="col">Phone</th>
                                <th scope="col">Email</th>
                                <th scope="col">Location ID</th>
                                <th scope="col"></th>
                                <th scope="col"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="currentOrganization" items="${organizationList}">
                                <tr>
                                    <th scope="row">
                                        <c:out value="${currentOrganization.organizationId}"/> 
                                    </th>
                                    <td>
                                        <c:out value="${currentOrganization.organizationName}"/>
                                    </td>
                                    <td>
                                        <c:out value="${currentOrganization.organizationDescription}"/>
                                    </td>
                                    <td>
                                        <c:out value="${currentOrganization.organizationPhone}"/>
                                    </td>
                                    <td>
                                        <c:out value="${currentOrganization.organizationEmail}"/>
                                    </td>
                                    <td>

                                        <c:out value="${currentOrganization.location.locationId}"/>

                                    </td>
                                    <td>
                                        <a href="#" onclick="editOrganization('${currentOrganization.organizationId}', '${currentOrganization.organizationName}',
                                                        '${currentOrganization.organizationDescription}', '${currentOrganization.organizationPhone}', '${currentOrganization.organizationEmail}')"> 
                                            Edit
                                        </a>
                                    </td>
                                    <td>
                                        <a href="deleteOrganization?organizationId=${currentOrganization.organizationId}">
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
        <script src="${pageContext.request.contextPath}/js/organization.js"></script>

    </body>
</html>
