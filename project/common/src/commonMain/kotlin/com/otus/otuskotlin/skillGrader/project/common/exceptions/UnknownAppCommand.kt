package com.otus.otuskotlin.skillGrader.project.common.exceptions

import com.otus.otuskotlin.skillGrader.project.common.models.AppCommand

class UnknownAppCommand(command: AppCommand) : Throwable("Wrong command $command at mapping toTransport stage")