function showModal(id) {
	var modalDelete = document.getElementById(id);
	if (modalDelete) {
		var modal = new bootstrap.Modal(modalDelete, {});
		modal.show();
	}
}
function deleteModal(num) {
	var numero = document.getElementById("id");
	if (numero) {
		numero.value = num;
		showModal("modalDelete");
	}
}