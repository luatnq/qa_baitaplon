package com.example.qa;

import com.example.qa.service.AuthService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {
    @Mock
    AuthService authService;
}
