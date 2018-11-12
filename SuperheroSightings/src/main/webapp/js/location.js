/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function editLocation(locationId, locationName, locationDescription, locationStreet, locationCity,
        locationState, locationZip, locationCountry, latitude, longitude) {

    $('#locationId').val(locationId);
    $('#locationName').val(locationName);
    $('#locationDescription').val(locationDescription);
    $('#locationStreet').val(locationStreet);
    $('#locationCity').val(locationCity);
    $('#locationState').val(locationState);
    $('#locationZip').val(locationZip);
    $('#locationCountry').val(locationCountry);
    $('#latitude').val(latitude);
    $('#longitude').val(longitude);
}