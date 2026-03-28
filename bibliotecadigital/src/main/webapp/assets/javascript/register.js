$(document).ready(function () {
    const inputEmail = $('#email');
    const emailError = $('#emailErrorText');
    const regex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

    $(`[data-bs-toggle="tooltipPass"]`).each(function () {
        new bootstrap.Tooltip(this);
    });


    inputEmail.on('input', function () {
        const mail = $(this).val();

        $(this).removeClass('is-invalid is-valid');
        emailError.removeClass('d-block').addClass('d-none');
        emailError.text('');

        if (mail === "") return;

        if (!regex.test(mail)) {
            $(this).addClass('is-invalid').removeClass('is-valid');
            emailError.text('⚠️ Ingrese un correo válido. (ej. correo@email.com)');
            emailError.removeClass('d-none').addClass('d-block');
            return;
        }

        $(this).addClass('is-valid');
    });

    $('#confirmPass').on('input', function () {
        $(this).removeClass('is-invalid');
        $('#passErrorText').addClass('d-none').text('');
    });

    $('form').on('submit', function (e) {
        const passError = $('#passErrorText');
        const password = $('#password').val();
        const confirm = $('#confirmPass').val();
        const email = inputEmail.val().trim();

        let valid = true;

        inputEmail.removeClass('is-invalid is-valid');
        emailError.addClass('d-none').text('');

        if (password !== confirm) {
            $('#confirmPass').addClass('is-invalid');
            passError.removeClass('d-none').addClass('d-block');
            passError.text('⚠️ Las contraseñas no coinciden.');
            valid = false;
        }

        if (!regex.test(email)) {
            inputEmail.addClass('is-invalid').removeClass('is-valid');
            emailError.removeClass('d-none').addClass('d-block');
            emailError.text('⚠️ Ingrese un correo válido. (ej. correo@email.com)');
            valid = false;
        }

        if (!valid) {
            e.preventDefault();
        }
    });

});