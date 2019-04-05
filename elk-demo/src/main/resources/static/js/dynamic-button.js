
    function GetDynamicTextBox(value){
        return '<input name = "DynamicTextBox" style="margin-bottom: 15px" type="text" class = " form-control"  value = "' + value + '" />' +
                '<input type="button" value="Remove" style="margin-bottom: 15px" class="btn btn-primary form-inline" onclick = "RemoveTextBox(this)" /> '
    }
    function AddTextBox() {
        var div = document.createElement('DIV');
        div.innerHTML = GetDynamicTextBox("");
        document.getElementById("TextBoxContainer").appendChild(div);
    }
     
    function RemoveTextBox(div) {
        document.getElementById("TextBoxContainer").removeChild(div.parentNode);
    }
     
    function RecreateDynamicTextboxes() {
        var values = eval('<%=Values%>');
        if (values != null) {
            var html = "";
            for (var i = 0; i < values.length; i++) {
                html += "<div   >" + GetDynamicTextBox(values[i]) + "</div>";
            }
            document.getElementById("TextBoxContainer").innerHTML = html;
        }
    }
 
    