package com.example.android.daggerwork.router

import com.example.android.daggerwork.routes.Route

class RouteNotFound(val route: Route, cause: Throwable) : RuntimeException("Route not found: $route", cause)
