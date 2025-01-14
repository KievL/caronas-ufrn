import { AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';

export function passwordValidator(): ValidatorFn {
  return (control: AbstractControl): ValidationErrors | null => {
    if (!control.value) {
      return null; // Se o campo estiver vazio, não aplicamos a validação
    }

    // Expressão regular para garantir:
    // - Pelo menos uma letra maiúscula (A-Z)
    // - Pelo menos um número (0-9)
    // - Pelo menos um caractere especial (!@#$%^&*)
    // - No mínimo 8 caracteres
    const regex = /^(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{8,128}$/;

    return regex.test(control.value) ? null : { passwordStrength: 'Senha deve conter ao menos uma letra maiúscula, um número e um caractere especial.' };
  };
}

