package cn.kais.immer.demo.base

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hjq.permissions.OnPermissionCallback
import com.hjq.permissions.XXPermissions

abstract class BaseAct : AppCompatActivity() {

    fun doCheckPermission(vararg mPermissions: String, callback: BaseCallback) {
        XXPermissions.with(this).permission(mPermissions).request(object : OnPermissionCallback {
            override fun onGranted(permissions: MutableList<String>, allGranted: Boolean) {
                if (!allGranted) {
                    Toast.makeText(baseContext, "获取部分权限成功，但部分权限未正常授予", Toast.LENGTH_SHORT).show()
                    return
                }
                Toast.makeText(baseContext, "获取所有权限成功", Toast.LENGTH_SHORT).show()
//                start(CaseImmersionAct::class.java)
                callback.onPermissionSuccess()
            }

            override fun onDenied(permissions: MutableList<String>, doNotAskAgain: Boolean) {
                if (doNotAskAgain) {
                    Toast.makeText(baseContext, "被永久拒绝授权，请手动授予权限", Toast.LENGTH_SHORT).show()
                    // 如果是被永久拒绝就跳转到应用权限系统设置页面
                    XXPermissions.startPermissionActivity(baseContext, permissions)
                } else {
                    Toast.makeText(baseContext, "获取权限失败", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

}