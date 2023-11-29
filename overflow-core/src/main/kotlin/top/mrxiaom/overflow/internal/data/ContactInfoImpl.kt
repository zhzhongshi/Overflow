@file:OptIn(LowLevelApi::class)
package top.mrxiaom.overflow.internal.data

import cn.evole.onebot.sdk.response.contact.FriendInfoResp
import cn.evole.onebot.sdk.response.contact.StrangerInfoResp
import cn.evole.onebot.sdk.response.group.GroupMemberInfoResp
import net.mamoe.mirai.LowLevelApi
import net.mamoe.mirai.contact.MemberPermission
import net.mamoe.mirai.data.FriendInfo
import net.mamoe.mirai.data.GroupHonorType
import net.mamoe.mirai.data.MemberInfo
import net.mamoe.mirai.data.StrangerInfo

class FriendInfoImpl(
    override val uin: Long,
    override val nick: String,
    override var remark: String,
    override val friendGroupId: Int = 0
) : FriendInfo

class StrangerInfoImpl(
    override val uin: Long,
    override val nick: String,
    override val fromGroup: Long = 0,
    override val remark: String = "",
): StrangerInfo

class MemberInfoImpl(
    override val honors: Set<GroupHonorType>,
    override val isOfficialBot: Boolean,
    override val joinTimestamp: Int,
    override val lastSpeakTimestamp: Int,
    override val muteTimestamp: Int,
    override val nameCard: String,
    override val permission: MemberPermission,
    override val point: Int,
    override val rank: Int,
    override val specialTitle: String,
    override val temperature: Int,
    override val nick: String,
    override val remark: String,
    override val uin: Long
): MemberInfo

val FriendInfo.asOnebot: FriendInfoResp
    get() = FriendInfoResp(uin, nick, remark)
val StrangerInfo.asOnebot: StrangerInfoResp
    get() = StrangerInfoResp(uin, nick, "", 0, "", 0, 0)

val GroupMemberInfoResp.asMirai: MemberInfoImpl
    get() = MemberInfoImpl(setOf(), false, joinTime, lastSentTime, 0, card,
        when(role) {
            "owner" -> MemberPermission.OWNER
            "admin" -> MemberPermission.ADMINISTRATOR
            else -> MemberPermission.MEMBER
        }, 0, 0, title, 0, nickname, "", userId)
