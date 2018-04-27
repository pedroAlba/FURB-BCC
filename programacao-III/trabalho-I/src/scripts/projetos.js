        $(function () {

            var tbProjetos = localStorage.getItem("tbProjetos");
            tbProjetos = JSON.parse(tbProjetos);
            if (tbProjetos == null)
                tbProjetos = [];

            for (var i in tbProjetos) {
                var p = JSON.parse(tbProjetos[i]);

                var ul = document.getElementById("mainList");
                var titulo = document.createElement("li");
                titulo.appendChild(document.createTextNode(p.TITULO));
                ul.appendChild(titulo);

                var desc = document.createElement("li");
                desc.appendChild(document.createTextNode(p.DESCRICAO));
                ul.appendChild(desc);

                var oImg = document.createElement("img");
                oImg.setAttribute('src', p.IMAGEM);
                oImg.setAttribute('height', '300px');
                oImg.setAttribute('width', '300px');
                ul.appendChild(oImg);

                var separator = document.createElement("hr");
                ul.appendChild(separator);
            }

            var dialog, form,
                titulo = $("#titulo"),
                descricao = $("#descricao"),
                imagem = $("#imagem"),
                allFields = $([]).add(titulo).add(descricao).add(imagem),
                tips = $(".validateTips");

            function checkLength(o, n, min, max) {
                return true;
            }

            //Persiste a imagem em base64
            function readURL(input) {
                if (input.files && input.files[0]) {
                    var reader = new FileReader();
                    reader.onload = function (e) {
                        localStorage.setItem("lastImage", e.target.result)
                    }
                    reader.readAsDataURL(input.files[0]);
                }
            }

            function addProjeto() {

                var valid = true;
                allFields.removeClass("ui-state-error");

                var projeto = JSON.stringify({
                    TITULO: titulo.val(),
                    IMAGEM: localStorage.getItem("lastImage"),
                    DESCRICAO: descricao.val()
                });

                localStorage.removeItem("lastImage");

                tbProjetos.push(projeto);
                localStorage.setItem("tbProjetos", JSON.stringify(tbProjetos));
                console.log(localStorage.getItem("tbProjetos"));

                dialog.dialog("close");
                window.location.reload(false);
                return valid;
            }

            dialog = $("#dialog-form").dialog({
                autoOpen: false,
                height: 400,
                width: 350,
                modal: true,
                buttons: {
                    "Inserir Projeto": addProjeto,
                    Cancel: function () {
                        dialog.dialog("close");
                        localStorage.removeItem("lastImage")
                    }
                },
                close: function () {
                    form[0].reset();
                    allFields.removeClass("ui-state-error");
                }
            });

            form = dialog.find("form").on("submit", function (event) {
                event.preventDefault();
                addProjeto();
            });

            $("#create-item").button().on("click", function () {
                dialog.dialog("open");
            });

            $("#clear").button().on("click", function () {
                tbProjetos = [];
                localStorage.setItem("tbProjetos", JSON.stringify(tbProjetos));
                window.location.reload(false);
            });

            $("#imagem").change(function () {
                readURL(this);
            })
        });