package subway

data class Station(val name: String) {

    override fun equals(other: Any?): Boolean {
        if (other is Station) {
            val otherStation: Station = other
            if (otherStation.name.equals(this.name, ignoreCase = true)) {
                return true
            }
        }
        return false
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }

}