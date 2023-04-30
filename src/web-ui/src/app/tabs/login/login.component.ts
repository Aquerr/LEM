import { Component } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../service/auth.service";
import {MaskService} from "../../service/mask.service";
import {Router} from "@angular/router";
import {LoginReuqest} from "../../model/jwt.model";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  signInForm = new FormGroup<{ username: FormControl<string>, password: FormControl<string> }>({
    username: new FormControl('', {nonNullable: true, validators: Validators.required}),
    password: new FormControl('', {nonNullable: true, validators: Validators.required})
  });

  errorMessage?: string;

  loading: boolean = false;

  constructor(private router: Router,
              private maskService: MaskService,
              private authService: AuthService) {
  }

  signIn() {
    this.loading = true;

    const formData = this.signInForm.value;

    let signInRequest: LoginReuqest = {
      username: formData.username === undefined ? '' : formData.username,
      password: formData.password === undefined ? '' : formData.password
    };

    console.log(signInRequest);

    this.maskService.show();
    this.authService.authenticate(signInRequest).subscribe({
      next: (response) => {
        console.log("test");
        this.router.navigate(['home'])
        this.maskService.hide();
      },
      error: (error) => {
        if (error.status === 401) {
          this.errorMessage = 'Bad username or password.';
        } else {
          this.errorMessage = 'Could not sign in because of a server error.'
        }
        this.maskService.hide();
      }
    });

    this.loading = false;
  }
}
