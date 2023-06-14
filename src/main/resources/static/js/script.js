const filesSlider = document.getElementById("filesInputSlider");

function addInput() {
    filesSlider.innerHTML += '<div class="mb-3"><label for="formFile" class="form-label">Picture 1</label>    <input class="form-control" type="file" id="formFile" name="files">  </div>';
}