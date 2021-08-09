package com.github.carvaldo.fimo.api.exception

import javax.servlet.UnavailableException

class LimitReachedException(princingUrl: String): UnavailableException("Limite gratuito atingido. Faça upgrade no plano em $princingUrl ou informe uma nova chave.")