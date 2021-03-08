let url;

document.addEventListener("DOMContentLoaded", function (){
    let editor;
    ClassicEditor
        .create(document.querySelector('#editor'))
        .then( newEditor => editor = newEditor)
        .catch(error => {
            console.error(error);
        });
    document.querySelector('#submit').addEventListener('click', () => {
        const editorData = editor.getData();
        $.ajax({
            url: url,
            method: "POST",
            data: {
                content: editorData,
                title:$('#title').val(),
                path:$('#path').val(),
                locale:$('#locale').val(),
                parentId:$('#parent-page option:selected').data('page-id')
            },
            success: function (msg) {
                console.log("ok");
            }
        })
    });
});