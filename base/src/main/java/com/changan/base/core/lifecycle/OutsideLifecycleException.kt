package com.changan.base.core.lifecycle

import androidx.annotation.Nullable

class OutsideLifecycleException(@Nullable detailMessage: String) : IllegalStateException(detailMessage)