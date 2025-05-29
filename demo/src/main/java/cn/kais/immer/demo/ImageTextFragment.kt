package cn.kais.immer.demo;

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment
import cn.kais.immer.demo.databinding.FragmentImageTextBinding

/**
 * @Author   : zhangwenchao
 * @Date     : 2020/7/8  6:57 PM
 * @email    : 869649338@qq.com
 * @Describe :
 */
class ImageTextFragment : Fragment() {

    companion object {
        private const val IMAGE = "image"
        fun newInstance(@DrawableRes imageRes: Int, block: ((Fragment) -> Unit)? = null) =
            ImageTextFragment().apply {
                arguments = Bundle().apply { putInt(IMAGE, imageRes) }
                statusBarBlock = block
            }
    }

    private val imageRes by lazy { arguments?.getInt(IMAGE) ?: 0 }

    private var statusBarBlock: ((Fragment) -> Unit)? = null
    private lateinit var bind: FragmentImageTextBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bind = FragmentImageTextBinding.inflate(layoutInflater)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind.topImage.setImageResource(imageRes)
        statusBarBlock?.invoke(this)
    }

}