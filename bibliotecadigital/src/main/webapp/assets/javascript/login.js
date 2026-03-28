$(document).ready(function () {
    const inputEmail = $('#email');
    const inputPass = $('#password');
    const loginErrorText = $('#loginErrorText');
    const regex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

    /*Toggle mostrar contraseña*/
    $('#toggleSeePass').on('click', function () {
        const type = inputPass.attr('type') === 'password' ? 'text' : 'password';

        inputPass.attr('type', type);
        $(this).toggleClass('bi bi-eye-slash-fill');
    })

    inputEmail.on('input', function () {
        const mail = $(this).val().trim();

        inputEmail.removeClass('is-invalid is-valid');
        loginErrorText.removeClass('d-block').addClass('d-none');
        loginErrorText.text('');

        if (mail === "") return;

        if (!regex.test(mail)) {
            $(this).addClass('is-invalid').removeClass('is-valid');
            return;
        }
        inputEmail.addClass('is-valid')
    });

    inputPass.on('input', function () {
        inputPass.removeClass('is-invalid');
        loginErrorText.removeClass('d-block').addClass('d-none');
        loginErrorText.text('')
    });

    $('form').on('submit', function (e) {
        const email = inputEmail.val().trim();
        const password = inputPass.val().trim();

        let valid = true;

        inputEmail.removeClass('is-invalid is-valid');
        inputPass.removeClass('is-invalid');
        loginErrorText.addClass('d-none').text('');

        if (!regex.test(email)) {
            inputEmail.addClass('is-invalid').removeClass('is-valid');
            loginErrorText.removeClass('d-none').addClass('d-block');
            loginErrorText.text('⚠️ Ingrese un correo válido. (ej. correo@email.com)');
            valid = false;
        }

        
        if (password === '') {
           inputPass.addClass('is-invalid');
            loginErrorText.removeClass('d-none').addClass('d-block');
            loginErrorText.text('⚠️ Ingrese su contraseña.');
            valid = false;
        }

        if (!valid) {
            e.preventDefault();
        }
    });
});