package ru.otus.otuskotlin.marketplace.api.log1.mapper

import com.otus.otuskotlin.skillGrader.project.common.AppContext
import com.otus.otuskotlin.skillGrader.project.common.models.AppError
import com.otus.otuskotlin.skillGrader.project.common.models.AppRequestId

import kotlin.time.Clock

fun AppContext.toLog(logId: String) = CommonLogModel(
    messageTime = Clock.System.now().toString(),
    logId = logId,
    source = "ok-marketplace",
    ad = toMkplLog(),
    errors = errors.map { it.toLog() },
)

private fun AppContext.toMkplLog(): MkplLogModel? {
    val adNone = MkplAd()
    return MkplLogModel(
        requestId = requestId.takeIf { it != AppRequestId.NONE }?.asString(),
        requestAd = adRequest.takeIf { it != adNone }?.toLog(),
        responseAd = adResponse.takeIf { it != adNone }?.toLog(),
        responseAds = adsResponse.takeIf { it.isNotEmpty() }?.filter { it != adNone }?.map { it.toLog() },
        requestFilter = adFilterRequest.takeIf { it != MkplAdFilter() }?.toLog(),
    ).takeIf { it != MkplLogModel() }
}

private fun MkplAdFilter.toLog() = AdFilterLog(
    searchString = searchString.takeIf { it.isNotBlank() },
    ownerId = ownerId.takeIf { it != MkplUserId.NONE }?.asString(),
    dealSide = dealSide.takeIf { it != MkplDealSide.NONE }?.name,
)

private fun AppError.toLog() = ErrorLogModel(
    message = message.takeIf { it.isNotBlank() },
    field = field.takeIf { it.isNotBlank() },
    code = code.takeIf { it.isNotBlank() },
    level = level.name,
)

private fun MkplAd.toLog() = AdLog(
    id = id.takeIf { it != MkplAdId.NONE }?.asString(),
    title = title.takeIf { it.isNotBlank() },
    description = description.takeIf { it.isNotBlank() },
    adType = adType.takeIf { it != MkplDealSide.NONE }?.name,
    visibility = visibility.takeIf { it != MkplVisibility.NONE }?.name,
    ownerId = ownerId.takeIf { it != MkplUserId.NONE }?.asString(),
    productId = productId.takeIf { it != MkplProductId.NONE }?.asString(),
    permissions = permissionsClient.takeIf { it.isNotEmpty() }?.map { it.name }?.toSet(),
)
