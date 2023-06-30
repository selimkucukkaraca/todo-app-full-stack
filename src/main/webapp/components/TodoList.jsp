<!DOCTYPE html>
<html>
<head>
    <title>Todo Listesi</title>
    <style>
        .todo-card {
            background-color: #f2f2f2;
            padding: 10px;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<h2>Todo Listesi</h2>

<div id="todo-container">
    <!-- Burada todo kartları dinamik olarak oluşturulacak -->
</div>

<form id="todo-form">
    <input type="text" id="todo-input" placeholder="Yeni bir todo girin...">
    <button type="submit">Ekle</button>
</form>

<script>
    document.getElementById("todo-form").addEventListener("submit", function(event) {
        event.preventDefault(); // Form gönderimini engelle

        var todoInput = document.getElementById("todo-input");
        var todoText = todoInput.value;

        if (todoText) {
            var todoCard = document.createElement("div");
            todoCard.className = "todo-card";
            todoCard.innerHTML = todoText;

            document.getElementById("todo-container").appendChild(todoCard);

            todoInput.value = "";
        }
    });
</script>
</body>
</html>
