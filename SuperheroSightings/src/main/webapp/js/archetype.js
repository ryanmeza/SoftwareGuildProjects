/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function editArchetype(archId, archName, archDesc, charId) {
    $('#archetypeId').val(archId);
    $('#archetypeName').val(archName);
    $('#archetypeDescription').val(archDesc);
    $('#characterTypeId').val(charId);
}