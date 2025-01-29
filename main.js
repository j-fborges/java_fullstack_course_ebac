$(document).ready(function () {
    $("header button").click(function () {
        $("form").slideDown();
    });

    $("#botao-cancelar").click(function () {
        $("form").slideUp();
    });

    $("form").on("submit", function (e) {
        e.preventDefault();

        const newTask = $("#new-task").val();
        if (newTask.trim() !== "") {
            const novoItem = $(`
                <li class="task-container">
                    ${newTask}
                </li>`);
            $("ul").append(novoItem);
            $(novoItem).slideDown(800);
            $("#new-task").val("");
        }
    });

    $("ul").on('click', '.task-container', (event) => {
        console.log("hey")
        $(event.target).toggleClass("done")
    })

});
