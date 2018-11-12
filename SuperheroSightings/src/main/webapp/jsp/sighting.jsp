<%-- 
    Document   : sighting
    Created on : Oct 19, 2018, 10:44:39 PM
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
        <title>H.E.R.O. Sighting</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">   
        <link href="${pageContext.request.contextPath}/css/superherosightings.css" rel="stylesheet">
    </head>
    <body>
        <div class="container-fluid">
            <h1>H.E.R.O. Sightings Database</h1>
            <hr/>
            <!------------------NAVIGATION BAR-->
            <div class="navbar">
                <nav class="navbar navbar-expand-lg navbar-light bg-light container-fluid">
                    <button class="navbar-toggler" type="button" data-toggle="collapse" 
                            data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" 
                            aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                        <div class="navbar-nav">
                            <a class="nav-item nav-link" href="${pageContext.request.contextPath}/home">Home</a>
                            <a class="nav-item nav-link" href="${pageContext.request.contextPath}/displayArchetypes">Archetype</a>
                            <a class="nav-item nav-link" href="${pageContext.request.contextPath}/displayLocations">Location</a>
                            <a class="nav-item nav-link" href="${pageContext.request.contextPath}/displayOrganizations">Organization</a>
                            <a class="nav-item nav-link active" href="${pageContext.request.contextPath}/displaySightings">Sighting</a>
                            <a class="nav-item nav-link" href="${pageContext.request.contextPath}/displaySuperpowers">Superpower</a>

                        </div>
                    </div>
                </nav>
            </div>

            <div class="row">
                <div class="col-md-3">
                    <h2>Sighting</h2>
                    <form class="form" role="form" method="POST" action="addSighting">
                        <div class="form-group row ">
                            <div class="col-md-12">


                                <label for="sightingId" class="col-form-label">Sighting Id</label>
                                <input type="text" class="col-sm-9 form-control" name="sightingId" id="sightingId" readonly/>


                            </div>
                            <div class="col-md-12">


                                <label for="sightingDate" class="col-form-label">Sighting Date: </label>
                                <input type="date" class="col-sm-9 form-control" name="sightingDate"  id="sightingDate" placeholder="Date"/>

                                <label for="locationId" class="col-form-label">Location ID:</label>
                                <select class="col-sm-9 form-control" name="locationId"  id="locationId">
                                    <c:forEach var="currentLocation" items="${locationList}">
                                        <option>
                                            <c:out value="${currentLocation.locationId}"/>
                                        </option>
                                    </c:forEach>
                                </select>

                                <label for="archetypeName" class="col-form-label">Archetype:</label>
                                <select class="col-sm-9 form-control" name="archetypeName"  id="archetypeName">
                                    <c:forEach var="currentArchetype" items="${archetypeList}">
                                        <option>
                                            <c:out value="${currentArchetype.archetypeId}"/>
                                        </option>
                                    </c:forEach>
                                </select>



                                <div>
                                    <button type="submit" value="Add/Update Sighting" class="btn btn-default form-group">Add/Update</button>
                                    <button type="reset" class="btn btn-default form-group">Reset</button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="col-md-9">
                    <h2>Sightings Identified</h2>
                    <table id="sightingTable" class="table table-striped">
                        <thead class="col-md-12">
                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">Date</th>
                                <th scope="col">Location Id</th>
                                <th scope="col">Location Name</th>
                                <th scope="col">Archetype</th>

                                <th scope="col"></th>
                                <th scope="col"></th>
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
                                        <c:out value="${currentSighting.sighting.location.locationName}"/>
                                    </td>
                                    <td>
                                        <c:out value="${currentSighting.archetypeName}"/>
                                    </td>

                                    <td>
                                        <a href="#" onclick="editSighting('${currentSighting.sighting.sightingId}', '${currentSighting.sighting.sightingDate}',
                                                '${currentSighting.sighting.location.locationId}', '${currentSighting.archetypeId}')"> 
                                            Edit
                                        </a>
                                    </td>
                                    <td>
                                        <a href="deleteSighting?sightingId=${currentSighting.sighting.sightingId}">
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
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" 
                integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" 
        crossorigin="anonymous"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/sighting.js"></script>


    </body>
</html>
