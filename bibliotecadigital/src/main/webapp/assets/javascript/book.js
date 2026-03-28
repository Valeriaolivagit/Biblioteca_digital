$(document).ready(function () {
    const inputQuantity = $('#quantity');
    const quantityErrorText = $('#quantityErrorText');

    inputQuantity.on('input', function () {
        const value = $(this).val().trim();
        const quantity = parseInt(value);

        $(this).removeClass('is-invalid is-valid');
        quantityErrorText.removeClass('d-block').addClass('d-none');
        quantityErrorText.text('');

        if (value === "") return;

        if (isNaN(quantity) || quantity <= 0) {
            $(this).addClass('is-invalid').removeClass('is-valid');
            quantityErrorText.text('⚠️ Ingrese una cantidad mayor a 0.');
            quantityErrorText.removeClass('d-none').addClass('d-block');
            return;
        }

        $(this).addClass('is-valid');
    });
});