<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>H.E.R.O. Sightings Database Home</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">    
        <link href="${pageContext.request.contextPath}/css/superherosightings.css" rel="stylesheet">  
    </head>
    <body>
        <div class="container-fluid">
            <h1>H.E.R.O. Sightings Database</h1>
            <hr/>
            <div class="navbar">
                <nav class="navbar navbar-expand-xl navbar-light bg-light container-fluid">
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                        <div class="navbar-nav">
                            <a class="nav-item nav-link active" href="${pageContext.request.contextPath}/home">Home</a>
                            <a class="nav-item nav-link" href="${pageContext.request.contextPath}/displayArchetypes">Archetype</a>
                            <a class="nav-item nav-link" href="${pageContext.request.contextPath}/displayLocations">Location</a>
                            <a class="nav-item nav-link" href="${pageContext.request.contextPath}/displayOrganizations">Organization</a>
                            <a class="nav-item nav-link" href="${pageContext.request.contextPath}/displaySightings">Sighting</a>
                            <a class="nav-item nav-link" href="${pageContext.request.contextPath}/displaySuperpowers">Superpower</a>

                        </div>
                    </div>
                </nav>                
            </div>
            <h2>Archetype Sightings and Information</h2>
            <div class="row" id="siteDescription">
                <div class="col-md-12">
                <p>THIS IS THE DESCRIPTION OF SITE FUNCTIONALITY</p>
                <br>
         
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <h2>Lastest Sightings Captured:</h2>
                    <table id="lastTenSightingsTable" class="table table-striped">
                        <thead class="col-md-12">
                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">Date</th>
                                <th scope="col">Location Id</th>
                                <th scope="col">Archetype</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="currentSighting" items="${tableList}">
                                <tr>
                                    <th scope="row">
                                        <c:out value="${currentSighting.sighting.sightingId}"/> 
                                    </th>
                                    <td>
                                        <c:out value="${currentSighting.sighting.sightingDate}"/>
                                    </td>
                                    <td>
                                        <c:out value="${currentSighting.sighting.location.locationId}"/>
                                    </td>
                                    <td>
                                        <c:out value="${currentSighting.archetypeName}"/>
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

    </body>
</html>

