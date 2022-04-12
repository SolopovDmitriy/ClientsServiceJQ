//PURE JS
document.addEventListener('DOMContentLoaded', function () {
    document.getElementById('loadButton').onclick = function (event) {
        event.preventDefault() // блокируеться стандартное поведение браузера
        ajaxSubmitForm() // после блокировки js делает собственную отправку данных
    }
})
// http://localhost:8080/

function ajaxSubmitForm() {
    let form = document.getElementById('clientForm')//обращаемся к форме
    let formData = new FormData(form)//обращаемся к данных на форме
    let json = JSON.stringify(Object.fromEntries(formData))//список пар ключ - значение,
    let request = new XMLHttpRequest() //объект с помощью , которого будет отправляться информация на сервер

    request.addEventListener('readystatechange', function () {
        console.log(request.readyState)
        if (request.readyState !== 4) return//если не 4 - ре, то ничего не делаем выходим из метода
        if (request.status === 200) {//отправка на сервер произошла успешно, все методы отработали
            let clients = JSON.parse(request.responseText) //превращаем JSON  методом  parse () в объект, return new ResponseEntity<>(clients, HttpStatus.OK) - ответ от сервера назад Js
            clients = {
                'clients': clients
            }
            const html = `
                <table>
                {{#clients}}
                <tr>
                    <td>{{id}}</td>
                    <td>{{surname}}</td>
                    <td>{{name}}</td>
                    <td>{{patronymic}}</td>
                    <td>{{email}}</td>
                    <td>{{birthDate}}</td>
                    <td>{{gender}}</td>
                </tr>
                {{/clients}}
                </table>
            `
            let result = mustache.render(html, clients)
            let output = document.getElementById('output')
            output.innerHTML = result//внедряем html код в   <div id="output"></div> в template/main.mustache
        } else console.log(request.status)
    })
    request.open('post', '/rest/clientForm') ///rest/clientForm - адрес, куда будут отправляться данные в @PostMapping (MainRestController)
    request.setRequestHeader('Content-Type', 'application/json; charset=utf-8')//формат данных и кодировка для отправки запроса методом send()
    request.send(json)// отправляем данные в формате json по адресу /rest/clientForm
}