package com.otus.otuskotlin.skillGrader.common.exceptions

import com.otus.otuskotlin.skillGrader.common.models.AppCommand

class UnknownAppCommand(command: AppCommand) : Throwable("Wrong command $command at mapping toTransport stage")