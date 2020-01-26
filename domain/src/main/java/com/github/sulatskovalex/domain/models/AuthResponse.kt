package com.github.sulatskovalex.domain.models

class AuthResponse(val user: User, val accessToken: String, val refreshToken: String)
