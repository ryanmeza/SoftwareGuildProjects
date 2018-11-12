<%-- 
    Document   : archetype
    Created on : Oct 19, 2018, 10:43:55 PM
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
        <title>H.E.R.O. Archetype</title>
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
                            <a class="nav-item nav-link" href="${pageContext.request.contextPath}/">Home</a>
                            <a class="nav-item nav-link active" href="${pageContext.request.contextPath}/displayArchetypes">Archetype</a>
                            <a class="nav-item nav-link" href="${pageContext.request.contextPath}/displayLocations">Location</a>
                            <a class="nav-item nav-link" href="${pageContext.request.contextPath}/displayOrganizations">Organization</a>
                            <a class="nav-item nav-link" href="${pageContext.request.contextPath}/displaySightings">Sighting</a>
                            <a class="nav-item nav-link" href="${pageContext.request.contextPath}/displaySuperpowers">Superpower</a>

                        </div>
                    </div>
                </nav>
            </div>

            <div class="row">
                <div class="col-md-2">
                    <h2>Archetype</h2>
                    <form class="form" role="form" method="POST" action="addArchetype">
                        <div class="form-group row ">
                            <div class="col-md-12">
                                <label for="archetypeId" class="col-form-label" >Archetype Id:</label>
                                <input type="text" class="col-sm-9 form-control" name="archetypeId" id="archetypeId" readonly/>
                            </div>
                            <div class="col-md-12">
                                <label for="archetypeName" class="col-form-label">Archetype Name:</label>
                                <input type="text" class="col-sm-9 form-control" name="archetypeName"  id="archetypeName" placeholder="Name"/>
                                <label for="archetypeDescription" class="col-form-label">Archetype Description:</label>
                                <input type="text" class="col-sm-9 form-control" name="archetypeDescription"  id="archetypeDescription" placeholder="Description"/>
                                <label for="characterTypeId" class="col-form-label">Character Type:</label>
                                <select class="col-sm-9 form-control" name="characterTypeId"  id="characterTypeId">
                                    <c:forEach var="currentCharacterType" items="${characterTypeList}">
                                        <option value="${currentCharacterType.characterTypeId}">
                                            <c:out value="${currentCharacterType.characterTypeName}"/>
                                        </option>
                                    </c:forEach>
                                </select>
                                <label for="superpowerId" class="col-form-label">Superpower:</label>
                                <select class="col-sm-9 form-control" name="superpowers"  id="superpowerId" required multiple> 
                                    <c:forEach var="currentSuperpower" items="${superpowerList}">
                                        <option value="${currentSuperpower.superpowerId}">
                                            <c:out value="${currentSuperpower.superpowerType}"/>
                                        </option>
                                    </c:forEach>
                                </select>
                                <label for="organizationId" class="col-form-label">Organization:</label>
                                <select class="col-sm-9 form-control" name="organizationId"  id="organizationId" required multiple>
                                    <c:forEach var="currentOrganization" items="${organizationList}">
                                        <option value="${currentOrganization.organizationId}">
                                            <c:out value="${currentOrganization.organizationName}"/>
                                        </option>
                                    </c:forEach>
                                </select>

                                <div>
                                    <button type="submit" value="Add/Update Archetype" class="btn btn-default form-group">Add/Update</button>
                                    <button type="reset" class="btn btn-default form-group">Reset</button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="col-md-10">
                    <h2>Archetypes Identified</h2>
                    <table id="oarchetypeTable" class="table table-striped">
                        <thead class="col-md-12">
                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">Name</th>
                                <th scope="col">Description</th>
                                <th scope="col">Character Type</th>
                                <th scope="col">Superpowers</th>
                                <th scope="col">Organizations</th>
                                <th scope="col"></th>
                                <th scope="col"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="currentArchetype" items="${archetypeList}">
                                <tr>
                                    <th scope="row">
                                        <c:out value="${currentArchetype.archetypeId}"/> 
                                    </th>
                                    <td>
                                        <c:out value="${currentArchetype.archetypeName}"/>
                                    </td>
                                    <td>
                                        <c:out value="${currentArchetype.archetypeDescription}"/>
                                    </td>
                                    <td>
                                        <c:out value="${currentArchetype.characterType.characterTypeName}"/>
                                    </td>
                                    <td>
                                        <ul>
                                            <c:forEach var="currentSuperpower" items="${currentArchetype.archetypeSuperpowers}">
                                                <li>                                                
                                                    <c:out value="${currentSuperpower.superpowerType}"/>
                                                </li>
                                            </c:forEach>
                                        </ul>


                                    </td>
                                    <td>
                                        <ul>
                                            <c:forEach var="currentOrganization" items="${currentArchetype.archetypeOrganizations}">
                                                <li> 
                                                    <c:out value="${currentOrganization.organizationName}"/>
                                                </li>
                                            </c:forEach>
                                        </ul>

                                    </td>
                                    <td>  
                                            <a href="#" onclick="editArchetype('${currentArchetype.archetypeId}', '${currentArchetype.archetypeName}',
                                                            '${currentArchetype.archetypeDescription}', '${currentArchetype.characterType.characterTypeId}')"> 
                                            Edit
                                        </a>
                                    </td>
                                    <td>
                                        <a href="deleteArchetype?archetypeId=${currentArchetype.archetypeId}">
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
        <script src="${pageContext.request.contextPath}/js/archetype.js"></script>
    </body>
</html>
