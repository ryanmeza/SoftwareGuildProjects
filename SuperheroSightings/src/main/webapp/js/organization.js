/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function editOrganization(organizationId, organizationName, organizationDescription,
        organizationPhone, organizationEmail) {
    $('#organizationId').val(organizationId);
    $('#organizationName').val(organizationName);
    $('#organizationDescription').val(organizationDescription);
    $('#organizationPhone').val(organizationPhone);
    $('#organizationEmail').val(organizationEmail);
}