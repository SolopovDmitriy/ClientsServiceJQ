const CLIENTS_TAB = `
<table class="table">
                {{#accounts}}
                <tr>
                    <td>{{id}}</td>
                    <td>{{amount}}</td>                  
                </tr>
                {{/accounts}}
                </table>
`

$(document).ready(function (){
    $('#loadButton').click(function (event){
        event.preventDefault()
        ajaxSubmitForm()
    })
})

function ajaxSubmitForm(){
    let form=$('#accountForm')[0]
    let formData=new FormData(form)
    let json=JSON.stringify(Object.fromEntries(formData))
    $.ajax({
        type: 'post',
        url: '/rest/accountForm',
        data: json,
        processData: false,
        contentType: 'application/json; charset=utf-8',
        success:function(data, textStatus, jqXHR){  //jqXHR = jQuery XMLHttpRequest
            console.log(data)
            let accounts = JSON.parse(jqXHR.responseText)
            accounts={
                'accounts':accounts
            }
            let result=mustache.render(CLIENTS_TAB, accounts)
            $('#output').html(result)
        },
        error:function(jqXHR, textStatus, errorThrown){
            alert(textStatus)
        }
    })
}