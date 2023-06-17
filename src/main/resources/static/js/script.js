const filesSlider = document.getElementById("filesInputSlider");
let count = 2;

//Function to add inputs for img
function addInput() {
    filesSlider.innerHTML += '<div class="mb-3"><label for="formFile" class="form-label">Picture' + count + '</label>    <input class="form-control" type="file" id="formFile" name="files">  </div>';
    count++;
}

//Check addPost form func
function checkForm(el) {
    let fil = "";

    const title = el.title.value;
    const descr = el.descr.value;

    if(title == "" || descr == ""){
        fil = " Title or descr must not be null";
        const error = document.getElementById("error");
        error.innerHTML = fil;
        return false;
    }
    return true;
}