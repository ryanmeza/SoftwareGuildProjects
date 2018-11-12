<%-- 
    Document   : location
    Created on : Oct 19, 2018, 10:44:18 PM
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
        <title>H.E.R.O. Location</title>
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
                            <a class="nav-item nav-link active" href="${pageContext.request.contextPath}/displayLocations">Location</a>
                            <a class="nav-item nav-link" href="${pageContext.request.contextPath}/displayOrganizations">Organization</a>
                            <a class="nav-item nav-link" href="${pageContext.request.contextPath}/displaySightings">Sighting</a>
                            <a class="nav-item nav-link" href="${pageContext.request.contextPath}/displaySuperpowers">Superpower</a>

                        </div>
                    </div>
                </nav>

            </div>
            <div class="row">
                <div class="col-md-4">
                    <h2>Location</h2>
                    <form class="form" role="form" method="POST" action="addLocation">
                        <div class="form-group row ">
                            <div class="col-md-12">
                                <label for="locationId" class="col-form-label">Location Id:</label>
                                <input type="text" class="col-sm-9 form-control" name="locationId" id="locationId"   readonly/>
                            </div>
                            <div class="col-md-12">
                                <label for="locationName" class="col-form-label">Location Name:</label>
                                <input type="text" class="col-sm-9 form-control" name="locationName"  id="locationName" placeholder="Location Name"/>

                                <label for="locationDescription" class="col-form-label">Location Description:</label>
                                <input type="text" class="col-sm-9 form-control" name="locationDescription"  id="locationDescription" placeholder="Location Description"/>

                                <label for="locationStreet" class="col-form-label">Location Street:</label>
                                <input type="text" class="col-sm-9 form-control" name="locationStreet"  id="locationStreet" placeholder="Location Street"/>

                                <label for="locationCity" class="col-form-label">Location City:</label>
                                <input type="text" class="col-sm-9 form-control" name="locationCity"  id="locationCity" placeholder="Location City"/>

                                <label for="locationState" class="col-form-label">Location State:</label>
                                <input type="text" class="col-sm-9 form-control" name="locationState"  id="locationState" placeholder="Location State"/>

                                <label for="locationZip" class="col-form-label">Location Zip:</label>
                                <input type="text" class="col-sm-9 form-control" name="locationZip"  id="locationZip" placeholder="Location Zip"/>

                                <label for="locationCountry" class="col-form-label">Location Country:</label>
                                <input type="text" class="col-sm-9 form-control" name="locationCountry"  id="locationCountry" placeholder="Location Country:"/>

                                <label for="latitude" class="col-form-label">Latitude:</label>
                                <input type="text" class="col-sm-9 form-control" name="latitude"  id="latitude" placeholder="Latitude"/>

                                <label for="longitude" class="col-form-label">Longitude:</label>
                                <input type="text" class="col-sm-9 form-control" name="longitude"  id="longitude" placeholder="Longitude"/>
                                <div>
                                    <button type="submit" value="Add/Update Location" class="btn btn-default form-group">Add/Update</button>
                                    <button type="reset" class="btn btn-default form-group">Reset</button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="col-md-8">
                    <h2>Locations Identified</h2>
                    <table id="locationTable" class="table table-striped">
                        <thead class="col-md-12">
                            <tr>
                                <th  scope="col">Location ID</th>
                                <th scope="col">Location Name</th>
                                <th scope="col">Street</th>
                                <th scope="col">City</th>
                                <th scope="col">State</th>
                                <th scope="col">Zip</th>
                                <th scope="col"></th>
                                <th scope="col"></th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="currentLocation" items="${locationList}">
                            <tr>
                            <th scope="row">
                            <c:out value="${currentLocation.locationId}"/> 
                            </th>
                            <td>
                            <c:out value="${currentLocation.locationName}"/>
                            </td>
                            <td>
                            <c:out value="${currentLocation.locationStreet}"/>
                            </td>
                            <td>
                            <c:out value="${currentLocation.locationCity}"/>
                            </td>
                            <td>
                            <c:out value="${currentLocation.locationState}"/>
                            </td>
                            <td>
                            <c:out value="${currentLocation.locationZip}"/>
                            </td>
                            <td>
                                <a href="#" onclick="editLocation('${currentLocation.locationId}', '${currentLocation.locationName}', 
                                             '${currentLocation.locationDescription}', '${currentLocation.locationStreet}', '${currentLocation.locationCity}', 
                                            '${currentLocation.locationState}', '${currentLocation.locationZip}', '${currentLocation.locationCountry}', 
                                            '${currentLocation.latitude}', '${currentLocation.longitude}')"> 
                                    Edit
                                </a>
                            </td>
                            <td>
                                <a href="deleteLocation?locationId=${currentLocation.locationId}">
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
    </div>
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/location.js"></script>

</body>
</html>
