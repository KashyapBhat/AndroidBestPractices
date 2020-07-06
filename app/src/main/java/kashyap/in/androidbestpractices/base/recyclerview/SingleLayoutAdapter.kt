package kashyap.`in`.androidbestpractices.base.recyclerview

open class SingleLayoutAdapter(
    private val layoutId: Int,
    clickListener: RVItemClickListener
) : MyBaseAdapter(clickListener) {

    override fun getLayoutIdForPosition(position: Int, dataAtThatPostion: Any): Int {
        return layoutId
    }
}